package com.sehinc.erosioncontrol.event;

import com.sehinc.common.event.EventRunnable;
import com.sehinc.common.event.JobCreator;
import com.sehinc.erosioncontrol.event.job.SendEmailOnProjectStatusChangeJobFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.task.TaskExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ErosionControlEventListener
    implements ApplicationListener
{
    static
    HashMap<Class, List<JobCreator>>
        registry;
    private
    TaskExecutor
        taskExecutor;

    static
    {
        loadRegistry();
    }

    public TaskExecutor getTaskExecutor()
    {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor)
    {
        this.taskExecutor =
            taskExecutor;
    }

    private static void loadRegistry()
    {
        if (registry
            == null)
        {
            registry =
                new HashMap<Class, List<JobCreator>>();
        }
        else
        {
            registry.clear();
        }
        registry.put(ProjectStatusChangeEvent.class,
                     createProjectStatusChangeEventList());
    }

    private static List<JobCreator> createProjectStatusChangeEventList()
    {
        ArrayList<JobCreator>
            list =
            new ArrayList<JobCreator>(1);
        list.add(new SendEmailOnProjectStatusChangeJobFactory());
        return list;
    }

    public void onApplicationEvent(ApplicationEvent event)
    {
        if (registry.containsKey(event.getClass()))
        {
            List<JobCreator>
                list =
                registry.get(event.getClass());
            for (JobCreator factory : list)
            {
                EventRunnable
                    task =
                    factory.createJob(event);
                taskExecutor.execute(task);
            }
        }
    }
}
