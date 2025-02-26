package euljiro.project.childcareproducts.domain.child;

import euljiro.project.childcareproducts.application.child.dto.ChildCommand;

public interface ChildService {

    public Child registerChildInfo(ChildCommand childCommand);

    public Child getChildBy(long childId);
}
