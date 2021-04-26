package com.example.demo.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable,Long>{
List<TimeTable> findByStd(Long std);
List<TimeTable> findByDate(String date);
List<TimeTable> findByStd(int std);
}