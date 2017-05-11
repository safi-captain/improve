package com.safi.dairy.base.config;

import freemarker.cache.URLTemplateLoader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by safi on 17/4/8.
 */
public class RemoteTemplateLoader extends URLTemplateLoader {
    private String remotePath;

    private List<String> includePaths;

    public RemoteTemplateLoader(String remotePath) {
        if (remotePath == null) {
            throw new IllegalArgumentException("remotePath is null");
        }
        this.remotePath = canonicalizePrefix(remotePath);
        if (this.remotePath.indexOf('/') == 0) {
            this.remotePath = this.remotePath.substring(this.remotePath.indexOf('/') + 1);
        }
    }

    @Override
    protected URL getURL(String name) {
        name = name.replace("_zh_CN", "");
        String fullPath = this.remotePath + name;
        System.out.println(fullPath);
        if ((this.remotePath.equals("/")) && (!isSchemeless(fullPath))) {
            return null;
        }

        URL url = null;
        try {
            url = new URL(fullPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static boolean isSchemeless(String fullPath) {
        int i = 0;
        int ln = fullPath.length();

        if ((i < ln) && (fullPath.charAt(i) == '/'))
            i++;

        while (i < ln) {
            char c = fullPath.charAt(i);
            if (c == '/')
                return true;
            if (c == ':')
                return false;
            i++;
        }
        return true;
    }

    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath;
    }

    public List<String> getIncludePaths() {
        return includePaths;
    }

    public void setIncludePaths(List<String> includePaths) {
        this.includePaths = includePaths;
    }
}
