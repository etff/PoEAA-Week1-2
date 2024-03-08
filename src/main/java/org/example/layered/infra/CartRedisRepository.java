package org.example.layered.infra;

import org.example.layered.query.dto.CartView;
import org.springframework.data.repository.CrudRepository;

public interface CartRedisRepository extends CrudRepository<CartView, Long> {
}
