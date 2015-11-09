package namoo.security.v3.web.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.Authentication;
import org.springframework.security.ui.rememberme.RememberMeProcessingFilter;

import namoo.security.v3.z_library.I18NUtil;

public class I2RememberMeProcessingFilter extends RememberMeProcessingFilter {

	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
		super.onSuccessfulAuthentication(request, response, authResult);
		LoginUser loginUser = LoginUser.getLoginUser();
		LoginUser.setLoginUserLocale(I18NUtil.getUserLocale(request, loginUser.getLanguage()));
		LoginUser.setLoginUserTimeZone(I18NUtil.getUserTimeZone(loginUser.getTimeZoneId()));
	}

}
