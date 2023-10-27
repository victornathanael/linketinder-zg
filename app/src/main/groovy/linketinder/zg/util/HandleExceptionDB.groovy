package linketinder.zg.util

static void handleExceptionDB(Exception e, String operationVerb) {
    e.printStackTrace()
    System.err.println("Erro ao " + operationVerb + " empresa no banco de dados.")
    System.exit(-42)
}