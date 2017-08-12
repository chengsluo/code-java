package dao;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
/*
* 2.3 返回SQL查询语句的实现
* 根据参数动态返回查询语句：
* */
public class ReturnQuery {
    public static String query(Object u){
        StringBuilder sqlStrBuilder = new StringBuilder();
        //1.获取到Class  
        Class c = u.getClass();
        //判断是否包含Table类型的注解  
        if(!c.isAnnotationPresent(Table.class)){
            return null;
        }
        //2.获取到table的名字  
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();
        //加入 where 1=1 ，是为了防止未来如果没有条件的情况下也不会报错  
        sqlStrBuilder.append("select * from ").append(tableName).append(" where 1=1");

        Field[] fArray = c.getDeclaredFields();   //获取类属性的所有字段，  
        //3.遍历所有的字段  
        for (Field field : fArray) {
            //4.处理每个字段对应的sql  
            //判断是否包含Column类型的注解  
            if(!field.isAnnotationPresent(Column.class)){
                continue;
            }
            //4.1.拿到字段上面注解的值，即Column注解的值  
            Column column = field.getAnnotation(Column.class);
            String columnName = column.value();
            //4.2.拿到字段的名  
            String filedName = field.getName();
            //获取相应字段的getXXX()方法  
            String getMethodName = "get" + filedName.substring(0, 1).toUpperCase()
                    + filedName.substring(1);
            //字段的值  
            Object fieldValue = null;//属性有int、String等，所以定义为Object类  
            try {
                Method getMethod = c.getMethod(getMethodName);
                fieldValue = getMethod.invoke(u);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //4.3.拼接sql  
            if(fieldValue==null || (fieldValue instanceof Integer && (Integer)fieldValue==0)){
                continue;
            }
            //未赋值时使用get方法,String会返回null,Integer会返回0
            
            sqlStrBuilder.append(" and ").append(filedName);
            if(fieldValue instanceof String){
                if(((String)fieldValue).contains(",")){
                    String[] values = ((String)fieldValue).split(",");
                    sqlStrBuilder.append(" in(");
                    for (String v : values) {
                        sqlStrBuilder.append("'").append(v).append("'").append(",");
                    }
                    sqlStrBuilder.deleteCharAt(sqlStrBuilder.length()-1);
                    sqlStrBuilder.append(")");
                }
                else{
                    sqlStrBuilder.append("=").append("'").append(fieldValue).append("'");
                }
            }
            else if(fieldValue instanceof Integer){
                sqlStrBuilder.append("=").append(fieldValue);
            }
        }
        return sqlStrBuilder.toString();

    }
}  