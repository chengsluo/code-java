/**
 * Created by chengs on 17-4-24.
 */
$(document).ready(function() {
    $("#leaveMessageForm").validate({
        errorElement : 'span',
        errorClass : 'help-block',

        rules : {
            txtUserName : "required",
            txtEmail : {
                required : true,
                email : true
            },
            password : {
                required : true,
                isPwd : true
            },
            confirm_password : {
                required : true,
                isPwd : true,
                equalTo : "#password"
            },
            phone : {
                required : true,
                isPhone : true
            },
            tel : {
                isTel : true
            },
            address : {
                minlength : 10
            }
        },
        messages : {
            txtUserName : "请输入姓名",
            txtEmail : {
                required : "请输入Email地址",
                email : "请输入正确的email地址"
            },
        },
        //自定义错误消息放到哪里
        errorPlacement : function(error, element) {
            element.next().remove();//删除显示图标
            element.after('<span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"></span>');
            element.closest('.form-group').append(error);//显示错误消息提示
        },
        //给未通过验证的元素进行处理
        highlight : function(element) {
            $(element).closest('.form-group').addClass('has-error has-feedback');
        },
        //验证通过的处理
        success : function(label) {
            var el=label.closest('.form-group').find("input");
            el.next().remove();//与errorPlacement相似
            el.after('<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>');
            label.closest('.form-group').removeClass('has-error').addClass("has-feedback has-success");
            label.remove();
        },

    });
});