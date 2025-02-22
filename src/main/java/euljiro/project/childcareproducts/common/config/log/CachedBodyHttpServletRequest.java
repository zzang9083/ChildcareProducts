//package euljiro.project.childcareproducts.common.config.log;
//
//import jakarta.servlet.ReadListener;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//
//import java.io.BufferedReader;
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//
///**
// * body는 stream으로 들어오기 때문에
// * 일회성으로 읽혀지는 stream의 특징으로 log를 한번 찍은 후, controller에서 읽히지 못하는 오류가 발생
// * 따라서 log로 읽혀지는 데이터들을 cash하여 필터에서 넘긴다.
// */
//public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {
//
//    private final byte[] cachedBody;
//
//    public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
//        super(request);
//        this.cachedBody = request.getInputStream().readAllBytes();
//    }
//
//    @Override
//    public ServletInputStream getInputStream() {
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cachedBody);
//        return new ServletInputStream() {
//            @Override
//            public boolean isFinished() {
//                return byteArrayInputStream.available() == 0;
//            }
//
//            @Override
//            public boolean isReady() {
//                return true;
//            }
//
//            @Override
//            public void setReadListener(ReadListener readListener) {
//                // No implementation needed
//            }
//
//            @Override
//            public int read() throws IOException {
//                return byteArrayInputStream.read();
//            }
//        };
//    }
//
//    @Override
//    public BufferedReader getReader() {
//        return new BufferedReader(new InputStreamReader(getInputStream()));
//    }
//}
