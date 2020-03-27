package com.example.amazonsync.Repository;

import com.example.amazonsync.DBModels.TaskDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TaskDetailsRepository extends JpaRepository<TaskDetails, Long> {

    Optional<TaskDetails> findByTaskDetailsId(Long id);
}