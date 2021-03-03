package com.cybertek.implementation;

import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Task;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.repository.TaskRepository;
import com.cybertek.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    TaskRepository taskRepository;

    @Mock
    TaskMapper taskMapper;

    @InjectMocks
    TaskServiceImpl taskService;

    @ParameterizedTest
    @ValueSource(longs = {1L,2L,3L})
    void findByIdTest(long arg){

        Task task = new Task();

        when(taskRepository.findById(arg)).thenReturn(Optional.of(task));
        when(taskMapper.convertToDto(task)).thenReturn(new TaskDTO());

        taskService.findById(arg);

        Mockito.verify(taskRepository).findById(arg);

    }

    @Test
    void findByIdBddTest(){

        //given
        Task task = new Task();
        BDDMockito.given(taskRepository.findById(Mockito.anyLong())).willReturn(Optional.of(task));
        BDDMockito.given(taskMapper.convertToDto(task)).willReturn(new TaskDTO());

        //when
        taskService.findById(Mockito.anyLong());


        //then
        BDDMockito.then(taskRepository).should().findById(Mockito.anyLong());
        BDDMockito.then(taskRepository).should(Mockito.never()).findById(-5L);

    }



}