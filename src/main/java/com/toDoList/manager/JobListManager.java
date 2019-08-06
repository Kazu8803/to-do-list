package com.toDoList.manager;

import com.toDoList.entity.Job;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class JobListManager {

    @Setter
    List<Job> jobsList;

    private AtomicLong nextId = new AtomicLong();

    public void addJob (@NonNull String description) {
        if (this.jobsList != null) {
            Job job = new Job(nextId.incrementAndGet(), description);
            this.jobsList.add(job);
        }
    }

    public Job getJobFromList (@NonNull long id) {
        for (Job job : this.jobsList) {
            if (job.getId() == id) {
                System.out.println(job.getDescription());
                System.out.println(job.getId());
                return job;
            }
        }
        throw new IllegalArgumentException();
    }
}
