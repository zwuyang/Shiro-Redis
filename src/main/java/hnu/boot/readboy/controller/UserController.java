package hnu.boot.readboy.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yzw
 * @since 2019-10-23
 */
@Slf4j
@RestController
public class UserController {


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String username,String password){
        try {

            password=new Md5Hash(password,username,3).toString();

            UsernamePasswordToken token=new UsernamePasswordToken(username,password);

            Subject subject=SecurityUtils.getSubject();

            String sid= (String) subject.getSession().getId();

            subject.login(token);

            return "success "+sid;
        }catch (Exception e){
            e.printStackTrace();
            return "error.";
        }
    }


    @RequestMapping(value = "/home",method = RequestMethod.GET)
    @RequiresRoles("user")
    public String home(){
        Subject subject=SecurityUtils.getSubject();
        subject.getSession();
        return "home";
    }



    @RequestMapping(value = "/user/admin",method = RequestMethod.GET)
    @RequiresRoles("admin")
    public String Admin(){
        return "admin";
    }

    @RequestMapping(value = "/autherror")
    public String autherror(int code){
        return code==1?"未登录":"未授权";
    }


}
