package Util;

public class UtilsSQL {
    public static String QUERY_CREATE_USUARIOS = "create table usuarios(ID integer primary key autoincrement, Nombre text, Correo text, Contrasenia text)";
    public static String QUERY_LOGIN = "select ID, Nombre,Correo from usuarios where Correo = ? and Contrasenia = ?";
    public static String SELECT_USUARIO_BY_NAME = "select ID from usuarios where Nombre = ?";
    public static String SELECT_USUARIO_BY_MAIL = "select ID from usuarios where Correo = ?";
    public static String SELECT_ALL_PARQUEO = "select ID,Matricula,Tiempo from PARQUEO Where ID_USUARIO= ?";

}
