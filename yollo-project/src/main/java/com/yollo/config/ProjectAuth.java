package com.yollo.config;


import com.yollo.models.User;
import com.yollo.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjectAuth {
    private final ProductRepository productRepository;

    public boolean hasRoleInProject(Long productId, Authentication auth, String role) {

        if(auth==null || !auth.isAuthenticated()) return false;
        User user = ((User) auth.getPrincipal());
        Long userId = user.getId();
        String userRole = user.getRole().name() ;
        return productRepository.existsByIdAndMembersId(productId, userId)
                && userRole.equalsIgnoreCase(role);
    }
}
