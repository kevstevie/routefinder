package org.jj.makcha.auth

import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

@RequestScope
@Component
class AuthContext(
    var id: Long? = null
)
