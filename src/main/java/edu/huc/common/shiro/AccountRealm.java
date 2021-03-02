package edu.huc.common.shiro;

import edu.huc.bean.User;
import edu.huc.common.constant.UserRole;
import edu.huc.common.response.RespCode;
import edu.huc.common.result.ResultUser;
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
    private JwtUtils jwtUtils;

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
        String userId = jwtUtils.getClaimByToken((String)jwtToken.getPrincipal()).getSubject();
        User user = userService.getById(Integer.valueOf(userId));
        if (user == null){
            throw new UnknownAccountException("用户不存在");
        }
        ResultUser resultUser = userService.convertUser(user);

        return new SimpleAuthenticationInfo(resultUser,jwtToken.getCredentials(),getName());
    }
}
