package jfk;

import javax.swing.*;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Input{
    private DefaultListModel<String> listaC;
    private URLClassLoader cl;
    public Class<?> loadClass;

    private void plikJar (File[] selectedFiles) {
        listaC.clear();
        JarFile jarFile = null;
        try {
            URL[] urls = new URL[selectedFiles.length];
            for (int i = 0; i < selectedFiles.length; i++)
                urls[i] = new URL("jar:file:" + selectedFiles[i].getAbsolutePath() + "!/");
            cl = URLClassLoader.newInstance(urls);
            for (File f : selectedFiles) {
                jarFile = new JarFile(f.getAbsolutePath());
                Enumeration<JarEntry> entries = jarFile.entries();

                while (entries.hasMoreElements()) {
                    JarEntry je = entries.nextElement();
                    if (je.isDirectory() || !je.getName().endsWith(".class")) {
                        continue;
                    }

                    String className = je.getName().substring(0, je.getName().length() - 6);
                    className = className.replace('/', '.');
                    try {
                        loadClass = cl.loadClass(className);
                        if (!loadClass.isAnnotationPresent(Description.class))
                            continue;

                        if (!ICallable.class.isAssignableFrom(loadClass))
                            throw new Exception("Class " + className + " does not implement the contract.");
                        else
                            listaC.addElement(loadClass.getName());
                    } catch (ClassNotFoundException exp) {
                        continue;
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } catch (IOException exp) {
        } finally {
            if (null != jarFile)
                try {
                    jarFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

     void plik()
    {
        JFileChooser chooser = new JFileChooser();

        chooser.setCurrentDirectory(new File("."));
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles;
            FileFilter filter=new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return f.isDirectory() || f.getName().endsWith(".jar");
                }
            };

            if (chooser.getSelectedFile().isDirectory()) {
                selectedFiles = chooser.getSelectedFile().listFiles(filter);
            } else selectedFiles = chooser.getSelectedFiles();
            plikJar(selectedFiles);
        }
    }

  public JList<String> Lista (){
        listaC = new DefaultListModel();
        JList<String> list;
        list = new JList<>(listaC);
        list.setBounds(20,20,20,20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                try {
                    loadClass = cl.loadClass(list.getSelectedValue());
                } catch (ClassNotFoundException e1) {
                }
            }
        });
        return list;
    }


}