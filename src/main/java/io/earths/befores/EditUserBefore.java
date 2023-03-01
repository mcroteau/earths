package io.earths.befores;

import net.plsar.implement.RouteEndpointBefore;
import net.plsar.model.*;
import net.plsar.security.SecurityManager;

public class EditUserBefore implements RouteEndpointBefore {

    @Override
    public BeforeResult before(FlashMessage flashMessage, ViewCache viewCache, NetworkRequest req, NetworkResponse resp, SecurityManager manager, BeforeAttributes beforeAttributes) {

        Long id = (Long) beforeAttributes.get("id");

        BeforeResult result = new BeforeResult();
        if(!manager.isAuthenticated(req)){
            flashMessage.set("user is nil fail.");
            result.setRedirectUri("redirect:/signin");
            return result;
        }

        String permission = "users::maintenance::" + id;

        if(!manager.hasPermission(permission, req)){
            flashMessage.set("permission fail.");
            result.setRedirectUri("redirect:/signin");
        }

        return result;

    }
}
