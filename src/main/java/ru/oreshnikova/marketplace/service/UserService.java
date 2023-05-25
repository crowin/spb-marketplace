package ru.oreshnikova.marketplace.service;

import ru.oreshnikova.marketplace.dto.OrderDto;
import ru.oreshnikova.marketplace.dto.Roles;
import ru.oreshnikova.marketplace.entity.User;
import ru.oreshnikova.marketplace.repository.RoleRepository;
import ru.oreshnikova.marketplace.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ru.oreshnikova.marketplace.config.WebSecurityConfig.passwordEncoder;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public void saveUser(User user) {
        var role = roleRepository.findByName(Roles.USER.toString());

        var newUser = new User(user.getName(), passwordEncoder().encode(user.getPassword()), List.of(role));
        userRepository.save(newUser);

        log.info("User {} is saved", user.getName());
    }

    public Optional<User> findUserByName(String name) {
        return userRepository.findByName(name);
    }

    public List<OrderDto> getUserOrders(String name) {
        var orders = findUserByName(name).get().getOrders();
        var ordersDto = new ArrayList<OrderDto>();

        orders.forEach(o ->
                ordersDto.add(new OrderDto(o.getId(), o.getUser(), o.getTotalSum(), o.getOrderDate().toString(), o.getProduct()))
        );

        return ordersDto;
    }
}
