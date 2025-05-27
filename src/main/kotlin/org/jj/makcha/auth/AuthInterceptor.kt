package org.jj.makcha.auth

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor

const val AUTH_INFO = "authInfo"

@Component
class AuthInterceptor(
    private val authContext: AuthContext
) : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }

        val authAnnotation = handler.getMethodAnnotation(Auth::class.java) ?: return true

        val authInfo = request.getSession(false).getAttribute(AUTH_INFO) as? AuthInfo
            ?: throw IllegalStateException("로그인 정보가 없습니다.")

        authContext.id = authInfo.id

        return true
    }
}
