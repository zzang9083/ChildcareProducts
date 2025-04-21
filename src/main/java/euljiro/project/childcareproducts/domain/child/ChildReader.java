package euljiro.project.childcareproducts.domain.child;

import java.util.*;


public interface ChildReader {

    List<Child> getAllActiveChildBy(String userKey);

    Child getChildBy(long childId);

    Child getChildBy(String childToken);
}
