package org.matt.auth;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.matt.daos.UserDAO;
import org.matt.models.Reguser;

/**
 * Custom Shiro Realm configured for hashed and salted passwords
 * @author Matt
 */
public class PriceWatcherRealm extends JdbcRealm {
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();

		if (username != null) {
			final Reguser user = UserDAO.getUserByEmail(username);

			if (user == null) {
				System.out.println(username + " does not exist in the databse!");
				return null;
			}

			// return salted credentials
			SaltedAuthenticationInfo info = 
					new UserCredSalt(username, user.getUserPassword(), user.getSalt());
			return info;
		} else {
			System.out.println("Username is null.");
			return null;
		}
	}
}