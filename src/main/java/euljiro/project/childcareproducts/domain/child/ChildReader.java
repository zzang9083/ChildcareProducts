package euljiro.project.childcareproducts.domain.child;

import java.util.*;


public interface ChildReader {

    List<Child> getAllActiveChildBy(String userKey);

    Child getChildBy(long childId);

    List<Child> getActiveChildrenBy(long groupId, Child.Status status);

    Child getChildBy(String childToken);
}
