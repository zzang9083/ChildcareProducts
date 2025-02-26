package euljiro.project.childcareproducts.application.complex.dto;

import com.google.common.collect.Lists;
import euljiro.project.childcareproducts.domain.child.Child;
import euljiro.project.childcareproducts.domain.product.inquiryhistory.ProductInquiryHistory;
import euljiro.project.childcareproducts.domain.user.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class LoginInfo {

    private final Long id;

    private final String userKey;

    private final String nickName;

    private final User.Status status;



    public LoginInfo(User user) {
        this.id = user.getId();
        this.userKey = user.getUserKey();
        this.nickName = user.getNickName();
        this.status = user.getStatus();
    }

    @Getter
    @ToString
    public static class LoginResponse {

        private final String userKey;
        private final User.Status status;

        private final String token;

        private final String refreshToken;

        private String groupToken;

        public LoginResponse(User user, String jwtToken, String refreshToken) {
            this.userKey = user.getUserKey();
            this.status = user.getStatus();
            this.token = jwtToken;
            this.refreshToken = refreshToken;
            //this.groupToken = user.getGroup().getGroupToken();

            if(user != null && User.Status.MATCHED == user.getStatus()) {
                this.groupToken = user.getGroup().getGroupToken();
            }


        }
    }

    @Getter
    @ToString
    public static class ReissueResponse {

        private final String userKey;

        private final User.Status status;

        private final String token;
        private final String refreshToken;

        private String groupToken;

        public ReissueResponse(User user, String jwtToken, String refreshToken) {
            this.userKey = user.getUserKey();
            this.status  = user.getStatus();
            this.token = jwtToken;
            this.refreshToken = refreshToken;

            if(user != null && User.Status.MATCHED == user.getStatus()) {
                this.groupToken = user.getGroup().getGroupToken();
            }



        }
    }

    @Getter
    @ToString
    public static class DashBoardResponse {

        private String childToken;

        private String childName;

        private LocalDate birthday;

        private Child.BirthStatus birthStatus;

        private List<InquiryHistory> histories = Lists.newArrayList();


        public DashBoardResponse(Child child, List<ProductInquiryHistory> voHistories) {
            this.childToken = child.getChildToken();
            this.childName = child.getChildName();
            this.birthday = child.getBirthday();
            this.birthStatus = child.getBirthStatus();

            if( voHistories != null && voHistories.size() > 0) {

                for(int i = 0 ; i < voHistories.size() ; i++) {
                    ProductInquiryHistory voHistory = voHistories.get(i);
                    InquiryHistory history =
                    InquiryHistory.builder()
                            .productToken(voHistory.getProductToken())
                            .productName(voHistory.getProductName())
                            .itemToken(voHistory.getItemToken())
                            .itemName(voHistory.getItemName())
                            .creationTime(voHistory.getCreationTime()).build();
                    histories.add(history);
                }

            }


        }

    }

    @Getter
    @Builder
    @ToString
    public static class InquiryHistory {

        private String productToken;

        private String productName;

        private String itemToken;

        private String itemName;

        private LocalDateTime creationTime;
    }

    @Getter
    @Builder
    @ToString
    public static class Main {
        private final Long id;

        private final String userToken;

        private final String nickName;

        private final String relationship;

        private final String status;
    }
}
