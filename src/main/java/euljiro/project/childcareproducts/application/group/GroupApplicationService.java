//package euljiro.project.childcareproducts.application.group;
//
//import euljiro.project.childcareproducts.domain.group.Group;
//import euljiro.project.childcareproducts.domain.group.GroupService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class GroupApplicationService {
//
//    private final GroupService groupService;
//
//
//    public void disableGroup(String groupToken) {
//
//        Group group = groupService.getGroupByToken(groupToken);
//
//        groupService.updateStatus(group, Group.Status.INACTIVE);
//
//
//    }
//
//
//}
