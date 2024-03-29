package edu.huc.common.shiro;

import edu.huc.common.vo.UserVo;
import edu.huc.service.IUserService;
import edu.huc.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Resource
    JwtUtils jwtUtils;
    @Resource
    private IUserService userService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        UserVo user = userService.getById(Integer.valueOf(userId));
        if(user == null){
            throw new UnknownAccountException("账号不存在");
        }
        return new SimpleAuthenticationInfo(user,jwtToken.getCredentials(),getName());
    }
}
