package com.order.controller;

import com.common.utils.HttpClient;
import com.common.utils.ResultUtils;
import com.common.utils.WXRequestParams;
import com.common.utils.WXUserInfo;
import com.order.utils.AesCbcUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.order.service.LoginService;

import java.util.HashMap;
import java.util.Map;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月16日
* @version v1.0
*/
@Api(tags = "登录接口")
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final static String APP_ID = "wx0a5e19ff41bf3930";
    private final static String APP_SECRET = "c6430309d7c60ebe3714fd6b14c39ea3";

	@Autowired
	private LoginService loginService;

	@ApiOperation("登录")
	@PostMapping(value = "/login")
	public ResultUtils login(String username, String password) {
		return loginService.login(username, password);
	}

	@ApiOperation("微信登录")
    @PostMapping(value = "/wxLogin")
	public ResultUtils weChatLogin(@RequestBody WXRequestParams params) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        String code = params.getCode();
        String grantType = "authorization_code";
        String param = "appid=" + APP_ID + "&secret=" + APP_SECRET + "&js_code=" + code + "&grant_type=" + grantType;
        String result = HttpClient.doGet(url, param);
        JSONObject resultJson = JSONObject.fromObject(result);
        String decryptResult = AesCbcUtil.decrypt(params.getEncryptedData(), resultJson.getString("session_key"), params.getIv(), "UTF-8");
        WXUserInfo userInfo = new WXUserInfo();
        if(decryptResult != null && decryptResult.length() > 0) {
            JSONObject wxUserInfoJson = JSONObject.fromObject(decryptResult);
            userInfo.setOpenId(resultJson.getString("openid"));
            userInfo.setNickName(wxUserInfoJson.getString("nickName"));
            userInfo.setGender(wxUserInfoJson.getString("gender"));
            userInfo.setCity(wxUserInfoJson.getString("city"));
            userInfo.setProvince(wxUserInfoJson.getString("province"));
            userInfo.setCountry(wxUserInfoJson.getString("country"));
            userInfo.setAvatarUrl(wxUserInfoJson.getString("avatarUrl"));
            userInfo.setUnionId(wxUserInfoJson.getString("unionId"));
        }
        return ResultUtils.success("", userInfo);
    }
}
