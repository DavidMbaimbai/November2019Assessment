package com.mbaimbai.assessment.model.repos;

import com.mbaimbai.assessment.model.LineItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineItemRepository extends JpaRepository<LineItem, Long> {
}
