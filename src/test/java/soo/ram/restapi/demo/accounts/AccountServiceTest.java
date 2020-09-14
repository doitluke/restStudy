package soo.ram.restapi.demo.accounts;


import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import soo.ram.restapi.demo.common.BaseTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


public class AccountServiceTest extends BaseTest {

    @Rule
    public ExpectedException expectedException;
    @Autowired
    AccountService accountService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void findByUserName() {


        String userName= "keesun@email.com";
        String password = "keesun";

        Account account = Account.builder()
                .email(userName)
                .password(password)
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();

        this.accountService.saveAccount(account);

        UserDetailsService userDetailsService = (UserDetailsService) accountService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

        assertThat(this.passwordEncoder.matches(password, userDetails.getPassword())).isEqualTo(true);

    }

    public void findByUsernameFail(){
        String userName = "random@email.com";
        try {
            accountService.loadUserByUsername(userName);
            fail("supposed to be failed");
        }catch(UsernameNotFoundException e){
            assertThat(e.getMessage()).containsSequence(userName);
        }
    }

    public void findByUsernameFail_type2(){

        //given
        String userName = "random@email.com";
        expectedException.expect(UsernameNotFoundException.class);
        expectedException.expectMessage(Matchers.containsString(userName));
        //when
        accountService.loadUserByUsername(userName);
    }

}