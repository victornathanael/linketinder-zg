package linketinder.zg.util

import javax.servlet.http.HttpServletResponse

class SendHTTPServletResponse {
    static void sendCreatedResponse(HttpServletResponse resp) {
        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_CREATED)
    }

    static void sendOkResponse(HttpServletResponse resp) {
        resp.setContentType("application/json")
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_OK)
    }

    static void sendBadRequestResponse(HttpServletResponse resp, String message) throws IOException {
        resp.setCharacterEncoding("UTF-8")
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST)
        resp.getWriter().write(message)
    }
}
