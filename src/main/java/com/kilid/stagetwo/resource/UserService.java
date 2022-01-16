package com.kilid.stagetwo.resource;

import com.kilid.stagetwo.model.User;
import com.kilid.stagetwo.model.User_Role;
import com.kilid.stagetwo.model.User_Subscription;
import com.kilid.stagetwo.repository.impl.UserRepositoryInterface;
import com.kilid.stagetwo.repository.impl.UserRoleRepositoryInterface;
import com.kilid.stagetwo.repository.impl.UserSubscriptionRepositoryInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService  {


    private final UserRepositoryInterface userRepositoryInterface;

    private final UserRoleRepositoryInterface userRoleRepository;

    private final UserSubscriptionRepositoryInterface userSubscriptionRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryInterface.findByName(username);
        Collection<SimpleGrantedAuthority> authorityCollection = new ArrayList<>();
        if(user == null){
            log.error("user name not found {}" + username);
            throw new UsernameNotFoundException(" user name not found {}" + username);
        }else {
            log.info(" user name found {}" + username + " user loaded : " + user.getName() + " password : " + user.getPassword());
        }
        //List<User_Role> userRole =  userRoleRepository.findByUser_id(user.getId());
        List<User_Role> userRole = new  ArrayList<>();
        userRole.add(new User_Role(1,1,"ROLE_ADMIN"));
        userRole.forEach(data->{
            authorityCollection.add(new SimpleGrantedAuthority(data.getRole()));
        });

        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),authorityCollection);
    }




    public User save(User user){
        log.info("new user insert");
       return userRepositoryInterface.save(user);
    }

    public List<User> userList(){
        log.info(String.valueOf(userRepositoryInterface.findAll()));
        return userRepositoryInterface.findAll();
    }
    public User finByUserName(String name){
        return userRepositoryInterface.findByName(name);
    }


    public User_Role saveUserRole(User_Role userRole){
        return userRoleRepository.save(userRole);
    }


    public User_Subscription saveUserSubscription(User_Subscription userSubscription){
        return userSubscriptionRepository.save(userSubscription);
    }

}
