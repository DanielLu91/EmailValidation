import com.signIn.service.SignInService;
import database.pojo.UserInfo;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yilu on 15/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestModule {
    private static Logger logger = Logger.getLogger(TestModule.class);
    @Resource
    private SignInService signInService = null;
    @Test
    public void test(){
        String code="1b565fcbd8b74153aa6383e12fd1e417";
        int id=this.signInService.isUserInDatabaseNotActive(code);
        if(id==0){
            System.out.print("error");
        }
        else{
            UserInfo userInfo=new UserInfo();
            userInfo.setUserid(id);
            userInfo.setCode("");
            userInfo.setStatus((byte) 1);
            this.signInService.active(userInfo);
            System.out.print("Success");
        }
////        UserInfo userInfo=signInService.select(1);
////        System.out.print(userInfo.getEmail());
//        signInService.insertNewUser(userInfo);
    }
}
