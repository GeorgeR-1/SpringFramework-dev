package com.cybertek.implementation;

import com.cybertek.dto.ProjectDTO;
import com.cybertek.dto.TaskDTO;
import com.cybertek.entity.Project;
import com.cybertek.entity.Task;
import com.cybertek.entity.User;
import com.cybertek.enums.Status;
import com.cybertek.mapper.ProjectMapper;
import com.cybertek.mapper.TaskMapper;
import com.cybertek.repository.TaskRepository;
import com.cybertek.repository.UserRepository;
import com.cybertek.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private TaskRepository taskRepository;
    private TaskMapper taskMapper;
    private ProjectMapper projectMapper;
    private UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper, ProjectMapper projectMapper,
                           UserRepository userRepository) {

        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
        this.projectMapper = projectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public TaskDTO findById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            return taskMapper.convertToDto(task.get());
        }

        return null;
    }

    @Override
    public List<TaskDTO> listAllTasks() {

        return taskRepository.findAll().stream()
                .map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public Task save(TaskDTO dto) {
        dto.setTaskStatus(Status.OPEN);
        dto.setAssignedDate(LocalDate.now());
        Task task = taskMapper.convertToEntity(dto);
        return taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        Optional<Task> foundTask = taskRepository.findById(id);
        if (foundTask.isPresent()) {
            foundTask.get().setIsDeleted(true);
            taskRepository.save(foundTask.get());
        }

    }

    @Override
    public void update(TaskDTO dto) {
        Optional<Task> task = taskRepository.findById(dto.getId());
        Task convertedTask = taskMapper.convertToEntity(dto);

        if (task.isPresent()) {
            convertedTask.setId(task.get().getId());
            convertedTask.setTaskStatus(task.get().getTaskStatus());
            convertedTask.setAssignedDate(task.get().getAssignedDate());
            taskRepository.save(convertedTask);
        }

    }

    @Override
    public int totalNonCompletedTasks(String projectCode) {
        return taskRepository.totalNonCompleteTasks(projectCode);
    }

    @Override
    public int totalCompletedTasks(String projectCode) {
        return taskRepository.totalCompletedTasks(projectCode);
    }


    @Override
    public void deleteByProject(ProjectDTO project) {

        List<TaskDTO> taskList = listAllByProject(project);
        taskList.forEach(taskDTO -> delete(taskDTO.getId()));

    }

    public List<TaskDTO> listAllByProject(ProjectDTO project) {

        return taskRepository.findAllByProject(projectMapper.convertToEntity(project))
                .stream().map(taskMapper::convertToDto).collect(Collectors.toList());

    }

    @Override
    public List<TaskDTO> listAllTaskByStatusIsNot(Status status) {
        User user = userRepository.findByUserName("tom@ct.com");
        List<Task> list = taskRepository.findAllByTaskStatusIsNotAndAssignedEmployee(status, user);

        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<TaskDTO> listAllTaskByProjectManager() {

        User user = userRepository.findByUserName("wedal");
        List<Task> tasks = taskRepository.findAllByProjectAssignedManager(user);

        return tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void updateStatus(TaskDTO dto) {

        Optional<Task> task = taskRepository.findById(dto.getId());

        if (task.isPresent()){
            task.get().setTaskStatus(dto.getTaskStatus());
            taskRepository.save(task.get());
        }

    }

    @Override
    public List<TaskDTO> listAllTasksByStatus(Status status) {

        User user = userRepository.findByUserName("tom@ct.com");
        List<Task> list = taskRepository.findAllByTaskStatusAndAssignedEmployee(status,user);

        return list.stream().map(taskMapper::convertToDto).collect(Collectors.toList());

    }

    @Override
    public List<TaskDTO> readAllByEmployee(User assignedEmployee) {

        List<Task> tasks = taskRepository.findAllByAssignedEmployee(assignedEmployee);

        return tasks.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }


}
