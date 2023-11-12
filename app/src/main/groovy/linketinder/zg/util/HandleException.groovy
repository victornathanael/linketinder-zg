package linketinder.zg.util

import javax.servlet.http.HttpServletResponse

class HandleException {
    static void handleExceptionDB(Exception e, String operationVerb) {
        e.printStackTrace()
        System.err.println("Erro ao " + operationVerb + " empresa no banco de dados.")
        System.exit(-42)
    }

    static void handleExceptionController(HttpServletResponse resp, Exception e, String errorMessage) throws IOException {
        e.printStackTrace()
        resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        resp.getWriter().write(errorMessage)
    }

}
