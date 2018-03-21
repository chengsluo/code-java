import java.util.ArrayList;
import java.util.Scanner;

class PhoneInfo {
    String companyName;
    String companyPhone;

    public PhoneInfo(String companyName, String companyPhone) {
        this.companyName = companyName;
        this.companyPhone = companyPhone;
    }
}

class PhoneService {
    ArrayList<PhoneInfo> phonelst = new ArrayList<PhoneInfo>();

    //添加企业号码
    public void addPhone(PhoneInfo phoneInfo) {
        phonelst.add(phoneInfo);
    }

    //获取指定公司的号码
    public String getPhoneNum(String companyName) {
        String phoneNum = null;
        for (int i = 0; i < phonelst.size(); i++) {
            if (phonelst.get(i).companyName.equals(companyName)) {
                phoneNum = phonelst.get(i).companyPhone;
                break;
            }
        }
        return phoneNum;
    }

    public static void main(String[] args) {
        PhoneService phoneService = new PhoneService();
        char contineflag = 'y';
        char seleflag;
        Scanner sc = new Scanner(System.in);
        String companyName, companyPhone;

        while (contineflag == 'y') {
            System.out.println("请输入操作选项：");
            System.out.println("1.添加企业服务电话");
            System.out.println("2.查询企业服务电话");
            seleflag = sc.nextLine().toCharArray()[0];
            switch (seleflag) {
            case '1':
                System.out.print("请输入企业名称：");
                companyName = sc.nextLine();
                System.out.print("请输入企业电话：");
                companyPhone = sc.nextLine();
                phoneService.addPhone(new PhoneInfo(companyName, companyPhone));
                break;
            case '2':
                System.out.print("请输入企业名称：");
                companyName = sc.nextLine();
                companyPhone = phoneService.getPhoneNum(companyName);
                System.out.println("此企业服务电话为：" + companyPhone);
                break;
            default:
                contineflag = 'n';
                System.out.println("输入错误，请重试！");
            }

        }
    }
}