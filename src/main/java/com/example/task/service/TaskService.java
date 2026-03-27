package com.example.task.service;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    /// TO BE IMPLEMENTED
    /// Handle and manage a list of Task objects
    private final List<Task> tasks = new ArrayList<>();

    // Ajouter une tâche
    public Task addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        return task;
    }

    // Supprimer une tâche par id
    public boolean removeTask(String id) {
        return tasks.removeIf(task -> task.getId().equals(id));
    }

    // Retourner la liste des tâches
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    // Marquer une tâche comme terminée
    public boolean markTaskAsDone(String id) {
        Optional<Task> task = tasks.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (task.isPresent()) {
            task.get().markAsDone();
            return true;
        }
        return false;
    }
}
