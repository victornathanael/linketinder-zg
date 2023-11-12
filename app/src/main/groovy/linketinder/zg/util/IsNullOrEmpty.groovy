package linketinder.zg.util

class IsNullOrEmpty {
     static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty()
    }

     static boolean isNullOrEmpty(Object value) {
        return value == null
    }
}
