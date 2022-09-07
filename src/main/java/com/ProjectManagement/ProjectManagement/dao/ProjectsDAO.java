package com.ProjectManagement.ProjectManagement.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ProjectManagement.ProjectManagement.Model.Project;
import com.ProjectManagement.ProjectManagement.exceptions.EntryAlreadyExists;

/*When JpaRepository or CrudRepository is used, SpringBoot provides automatic
implementation for all the operations. For which operations implementation
is provided ?

*/
@Repository
public interface ProjectsDAO extends JpaRepository<Project, Integer>{
}
