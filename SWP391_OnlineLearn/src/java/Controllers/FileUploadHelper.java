/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.http.Part;

/**
 *
 * @author Duy Hiep
 */
public class FileUploadHelper {

    public String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public void getFileContent(String fileName, Part part, String path) {
        FileOutputStream out = null;
        InputStream filecontent = null;
        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            try {
                filecontent = part.getInputStream();
            } catch (IOException ex) {
                Logger.getLogger(EditSettingsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (FileNotFoundException fne) {
        } catch (IOException ex) {
            Logger.getLogger(EditSettingsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(EditSettingsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (filecontent != null) {
                try {
                    filecontent.close();
                } catch (IOException ex) {
                    Logger.getLogger(EditSettingsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public String getUrlCloudinaryForEdit(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dvr6kh3fs",
                            "api_key", "845592666737482",
                            "api_secret", "92VOfJo1EKYA9DDug8IpQQWSD10",
                            "folder", "blog",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getUrlCloudinaryForAdd(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dvr6kh3fs",
                            "api_key", "845592666737482",
                            "api_secret", "92VOfJo1EKYA9DDug8IpQQWSD10",
                            "folder", "upload",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getUrlCloudinaryForEditSubject(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dnofs4hkz",
                            "api_key", "696172155258237",
                            "api_secret", "RXkT2b-mshpYdv6ydQzXDjuZfzY",
                            "folder", "Subject",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getUrlCloudinaryForAddSubject(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dnofs4hkz",
                            "api_key", "696172155258237",
                            "api_secret", "RXkT2b-mshpYdv6ydQzXDjuZfzY",
                            "folder", "UploadSubject",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getUrlCloudinaryForEditSlide(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dnofs4hkz",
                            "api_key", "696172155258237",
                            "api_secret", "RXkT2b-mshpYdv6ydQzXDjuZfzY",
                            "folder", "Slide",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getUrlCloudinaryForAddSlide(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dnofs4hkz",
                            "api_key", "696172155258237",
                            "api_secret", "RXkT2b-mshpYdv6ydQzXDjuZfzY",
                            "folder", "uploadSlide",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String getUrlCloudinaryForEditUser(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dnofs4hkz",
                            "api_key", "696172155258237",
                            "api_secret", "RXkT2b-mshpYdv6ydQzXDjuZfzY",
                            "folder", "Avatar",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getUrlCloudinaryForAddUser(File fileUpload, String filename) {
        try {
            Cloudinary cloudinary = new Cloudinary();
            Map upload = cloudinary.uploader().upload(fileUpload,
                    ObjectUtils.asMap("cloud_name", "dnofs4hkz",
                            "api_key", "696172155258237",
                            "api_secret", "RXkT2b-mshpYdv6ydQzXDjuZfzY",
                            "folder", "UploadAvatar",
                            "public_id", filename,
                            "overwrite", true));
            fileUpload.delete();
            return upload.get("url").toString();
        } catch (IOException ex) {
            Logger.getLogger(FileUploadHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
