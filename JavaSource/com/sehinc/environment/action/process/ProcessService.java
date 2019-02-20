package com.sehinc.environment.action.process;

import com.sehinc.environment.db.asset.EnvAsset;
import com.sehinc.environment.db.process.EnvProcess;
import com.sehinc.environment.db.processasset.EnvProcessAsset;
import com.sehinc.environment.resources.ApplicationResources;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProcessService
{
    private static
    Logger
        LOG =
        Logger.getLogger(ProcessService.class);

    public ProcessService()
    {
    }

    public static List<EnvAsset> getAssetsForProcess(Integer processId)
    {
        List<EnvAsset>
            assets =
            new ArrayList<EnvAsset>();
        List<EnvProcessAsset>
            pa =
            EnvProcessAsset.findActiveByProcessId(processId);
        for (EnvProcessAsset epa : pa)
        {
            Integer
                assetId =
                epa.getAssetId();
            if (assetId
                != null)
            {
                EnvAsset
                    asset =
                    new EnvAsset(assetId);
                try
                {
                    if (asset.load())
                    {
                        assets.add(asset);
                    }
                }
                catch (Exception e)
                {
                    Object[]
                        parameters =
                        {assetId};
                    LOG.error(ApplicationResources.getProperty("asset.error.load.failed",
                                                               parameters));
                    LOG.error(e.getMessage());
                    return null;
                }
            }
        }
        return assets;
    }

    public static void deleteAttachedProcessAssets(Integer processId)
    {
        List<EnvProcessAsset>
            pa =
            EnvProcessAsset.findAllByProcessId(processId);
        for (EnvProcessAsset epa : pa)
        {
            try
            {
                epa.delete();
            }
            catch (Exception e)
            {
                LOG.error(ApplicationResources.getProperty("process.asset.delete.failed"));
                LOG.error(e.getMessage());
            }
        }
    }

    public static void deleteProcess(Integer processId)
    {
        EnvProcess
            envProcess =
            new EnvProcess(processId);
        try
        {
            envProcess.load();
            envProcess.delete();
        }
        catch (Exception e)
        {
            Object[]
                parameters =
                {processId};
            LOG.error(ApplicationResources.getProperty("process.delete.page.failed",
                                                       parameters));
            LOG.error(e.getMessage());
        }
    }

    public static List<EnvProcess> findChildrenAndDelete(List<EnvProcess> processList)
    {
        for (EnvProcess process : processList)
        {
            Integer
                processId =
                process.getId();
            try
            {
                List<EnvProcess>
                    children =
                    EnvProcess.findByParentProcessId(processId);
                if (children
                    != null
                    && children.size()
                       == 0)
                {
                    deleteAttachedProcessAssets(processId);
                    deleteProcess(processId);
                    return null;
                }
                else
                {
                    findChildrenAndDelete(children);
                    deleteAttachedProcessAssets(processId);
                    deleteProcess(processId);
                    return null;
                }
            }
            catch (Exception e)
            {
                Object[]
                    parameters =
                    {processId};
                LOG.error(ApplicationResources.getProperty("process.delete.page.failed",
                                                           parameters));
                LOG.error(e.getMessage());
            }
        }
        return null;
    }
}
