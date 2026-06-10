import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import com.example.task.service.TaskService;
import com.example.task.service.Task;

class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    void testAddTask() {
        Task task = service.addTask("Faire les courses");

        assertNotNull(task.getId());
        assertEquals("Faire les courses", task.getDescription());
        assertEquals(Task.Status.EN_COURS, task.getStatus());
    }

    @Test
void testGetTasks() {
    int initialSize = service.getTasks().size();

    service.addTask("Task 1");
    service.addTask("Task 2");

    assertEquals(initialSize + 2, service.getTasks().size());
}

@Test
void testRemoveTask() {
    Task task = service.addTask("Task à supprimer");

    int initialSize = service.getTasks().size();

    service.removeTask(task.getId());

    assertEquals(initialSize - 1, service.getTasks().size());
}

    @Test
    void testMarkTaskAsDone() {
        Task task = service.addTask("À terminer");

        boolean updated = service.markTaskAsDone(task.getId());

        assertTrue(updated);
        assertEquals(Task.Status.TERMINE, task.getStatus());
    }

    @Test
    void testMarkTaskAsDone_unknownId() {
        boolean updated = service.markTaskAsDone("fake-id");

        assertFalse(updated);
    }
}