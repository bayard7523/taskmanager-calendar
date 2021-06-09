package testmodel;

import com.taskmanager.model.Task;
import com.taskmanager.repository.ITaskRepository;
import com.taskmanager.service.TaskService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TestTaskCrudOperations {
    @Mock
    private ITaskRepository iTaskRepository;

    @InjectMocks
    private TaskService taskService;

//    @Test
//    public void getAllTasksTest() {
//        List<Task> tasks = new ArrayList<>();
//        Task writeTest = new Task(1L, "Write test", "Do test", LocalDate.of(2021, 6, 6), LocalTime.of(12, 36));
//        Task callParents = new Task(2L, "Call parents", "Make call", LocalDate.of(2022, 8, 5), LocalTime.of(18, 22));
//        Task playGame = new Task(3L, "Play game", "Play dota", LocalDate.of(2020, 4, 28), LocalTime.of(23, 45));
//        Task createSpringProject = new Task(4L, "Create Spring Project", "Spring boot project", LocalDate.of(2018, 12, 29), LocalTime.of(22, 12));
//
//        tasks.add(writeTest);
//        tasks.add(callParents);
//        tasks.add(playGame);
//        tasks.add(createSpringProject);
//
//        given(iTaskRepository.findAll()).willReturn(tasks);
//
//        List<Task> list = taskService.getAllTasks();
//
//        Assertions.assertEquals(list, tasks);
//    }
//
//    @Test
//    public void getTaskByIdTest() {
//        Long id = 1L;
//        Task expected = new Task(1L, "Write test", "Do test", LocalDate.of(2021, 6, 6), LocalTime.of(12, 36));
//
//        given(iTaskRepository.findById(id)).willReturn(Optional.of(expected));
//
//        Task actual = taskService.getTask(id);
//
//        Assertions.assertEquals(actual,expected);
//    }
//
//    @Test
//    public void delete() {
//        Long id = 1L;
//        taskService.deleteTask(id);
//
//        verify(iTaskRepository, times(1)).deleteById(id);
//    }


}
