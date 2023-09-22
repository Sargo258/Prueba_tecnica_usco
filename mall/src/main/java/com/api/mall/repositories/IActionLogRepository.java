package com.api.mall.repositories;

import com.api.mall.models.ActionLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActionLogRepository  extends JpaRepository<ActionLog, Long> {
}
