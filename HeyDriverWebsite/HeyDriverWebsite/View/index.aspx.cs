using HeyDriverWebsite.Controller;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace HeyDriverWebsite.View
{
    public partial class index : System.Web.UI.Page
    {
        public static List<String> brands = new List<string>();
        public static List<String> models = new List<string>();
        private DataSet ds = new DataSet();
        private DataTable dt = new DataTable();
        public static String modelos = "";
        public static String title1 = "Consumo desproporcionado de combustible";
        public static String title2 = "Mezcla de Aire/Combustible muy pobre";
        public static String title3 = "Mezcla de Aire/Combustible muy rica";
        public static String title4 = "Sensor MAF sucio o averiado";
        public static String title5 = "Inyectores sucios o averiados";
        public static String title6 = "Bobina Averiada";
        public static String title7 = "Bujías propensas a daños";
        public static String title8 = "Vehículo propenso a recalentamiento";
        public static String title9 = "Radiador Averiado";
        public static String title10 = "Alternador defectuoso";
        public static int value1 = 0;
        public static int value2 = 0;
        public static int value3 = 0;
        public static int value4 = 0;
        public static int value5 = 0;
        public static int value6 = 0;
        public static int value7 = 0;
        public static int value8 = 0;
        public static int value9 = 0;
        public static int value10 = 0;
        protected void Page_Load(object sender, EventArgs e)
        {
            try
            {
                brands.Clear();
                string connstring = String.Format("Server={0};Port={1};" +
                    "User Id={2};Password={3};Database={4};",
                    "localhost", "5432", "heydriver",
                    "h3yDr1v3r", "heydriverdb");
                // Making connection with Npgsql provider
                NpgsqlConnection conn = new NpgsqlConnection(connstring);
                conn.Open();
                // quite complex sql statement
                string sql = "SELECT BRANDNAME FROM BRAND";
                // data adapter making request from our connection
                NpgsqlDataAdapter da = new NpgsqlDataAdapter(sql, conn);
                ds.Reset();
                // filling DataSet with result from NpgsqlDataAdapter
                da.Fill(ds);
                conn.Close();
                String marcas = "";
                for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
                {
                    marcas = marcas + "<option value=\"" + ds.Tables[0].Rows[i][0].ToString() + "\" label=\"" + "" + "\" >";
                    brands.Add(ds.Tables[0].Rows[i][0].ToString());
                }
                listado_marcas.InnerHtml = marcas;
                String marcas1 = "<option value=\"" + "Todas las marcas" + "\" label=\"" + "" + "\" >" + marcas;
                marcas_listado.InnerHtml = marcas1;
            }
            catch (Exception ex)
            {
                Response.Write("<script>alert('Ha ocurrido un error, al cargar la página, por favor refresque la página');</script>");
            }
        }


        [System.Web.Services.WebMethod]
        public static String GetModelos(String marca)
        {
            try
            {
                string connstring = String.Format("Server={0};Port={1};" +
                    "User Id={2};Password={3};Database={4};",
                    "localhost", "5432", "heydriver",
                    "h3yDr1v3r", "heydriverdb");
                // Making connection with Npgsql provider
                NpgsqlConnection conn = new NpgsqlConnection(connstring);
                conn.Open();
                // quite complex sql statement
                string sql = "SELECT M.MODELNAME, B.BRANDNAME FROM MODEL M, BRAND B WHERE M.MODEL_FK_BRAND = B.BRANDNAME AND B.BRANDNAME = '" + marca + "'";
                // data adapter making request from our connection
                NpgsqlDataAdapter da = new NpgsqlDataAdapter(sql, conn);
                DataSet ds = new DataSet();
                ds.Reset();
                // filling DataSet with result from NpgsqlDataAdapter
                da.Fill(ds);
                conn.Close();
                String modelos = "";
                Boolean correcto = false;
                foreach (String brand in brands)
                {
                    if (brand.Equals(marca))
                    {
                        correcto = true;
                    }
                }
                if (correcto)
                {
                    models.Clear();
                    modelos = modelos + "<option value=\"Sólo usar la marca\" label=\"" + "" + "\" >";
                    for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
                    {
                        modelos = modelos + "<option value=\"" + ds.Tables[0].Rows[i][0].ToString() + "\" label=\"" + "" + "\" >";
                        models.Add(ds.Tables[0].Rows[i][0].ToString());
                    }
                    return modelos;
                }
                else
                {
                    if (!marca.Equals(""))
                    {
                        return "Por favor seleccione un vehículo del listado mostrado";
                    }
                    else
                    {
                        return "No hay nada";
                    }
                }
            }
            catch (Exception ex)
            {
                return "Ha ocurrido un error, por favor vuelva a intentar";
            }
        }


        [System.Web.Services.WebMethod]
        public static String GetDatos(String marca, String modelo)
        {
            controller controlador = new controller();
            return controlador.GetDatos(marca, modelo, brands, models);
        }


        [System.Web.Services.WebMethod]
        public static String GetDatosFallas(String falla, String marca)
        {
            controller controlador = new controller();
            return controlador.GetDatosFallas(falla, marca, brands, models);
        }


        [System.Web.Services.WebMethod]
        public static String GetFallasVIN(String vin)
        {
            controller controlador = new controller();
            return controlador.GetFallasVIN(vin);
        }
    }
}