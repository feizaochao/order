package com.order.controller;

import com.common.utils.HttpClient;
import com.common.utils.ResultUtils;
import com.common.utils.WXRequestParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.common.utils.R;
import com.order.service.LoginService;

/**
* @Description: TODO
* @author Robb G C LUO
* @date 2019年11月16日
* @version v1.0
*/
@Api(tags = "登录接口")
@RestController
public class LoginController {

    private final static String APPID = "wx0a5e19ff41bf3930";
    private final static String APPSECRET = "";

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
        String appId = "wx0a5e19ff41bf393";
        String appSecret = "c6430309d7c60ebe3714fd6b14c39ea3";
        String code = params.getCode();
        String grantType = "authorization_code";
        String param = appId + "&" + appSecret + "&" + code + "&" + grantType;
        String result = HttpClient.doGet(url, param);
        return ResultUtils.success("", result);
    }
}
