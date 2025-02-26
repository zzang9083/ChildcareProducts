package euljiro.project.childcareproducts.domain.child;

import java.util.*;


public interface ChildReader {

    List<Child> getAllActiveChildBy(String userKey);

    Optional<Child> getChildBy(long childId);
}
