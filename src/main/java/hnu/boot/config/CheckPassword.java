package hnu.boot.config;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Classname CheckPassword
 * @Description TODO
 * @Date 2019/10/23 16:05
 * @Created by yz
 */
public class CheckPassword {

    public static void main(String[] args) {
        System.out.println(new Md5Hash("11","1",3).toString());
    }
}