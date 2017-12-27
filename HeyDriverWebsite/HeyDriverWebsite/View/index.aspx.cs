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
        private static List<String> brands = new List<string>();
        private static List<String> models = new List<string>();
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
            marcas_listado.InnerHtml = marcas;
        }

        [System.Web.Services.WebMethod]
        public static String GetModelos(String marca)
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
                return "Por favor seleccione un vehículo del listado mostrado";
            }
        }

        [System.Web.Services.WebMethod]
        public static String GetDatos(String marca, String modelo)
        {
            string connstring = String.Format("Server={0};Port={1};" +
                "User Id={2};Password={3};Database={4};",
                "localhost", "5432", "heydriver",
                "h3yDr1v3r", "heydriverdb");

            Boolean correcto = false;
            foreach (String brand in brands)
            {
                if (brand.Equals(marca))
                {
                    correcto = true;
                }
            }
            Boolean correcto2 = false;
            if (modelo.Equals("Sólo usar la marca"))
            {
                correcto2 = true;
            }
            foreach (String model in models)
            {
                if (model.Equals(modelo))
                {
                    correcto2 = true;
                }
            }
            String results = "";
            if (correcto && correcto2)
            {
                NpgsqlConnection conn = new NpgsqlConnection(connstring);
                conn.Open();
                NpgsqlDataAdapter da = new NpgsqlDataAdapter();
                if (modelo.Equals("Sólo usar la marca"))
                {
                    string sql = "SELECT ALL_FAILURES_STATISTICS_by_brand('" + marca + "');";
                    da = new NpgsqlDataAdapter(sql, conn);
                }
                else
                {
                    string sql = "SELECT ALL_FAILURES_STATISTICS_by_model('" + marca + "', '" + modelo + "');";
                    da = new NpgsqlDataAdapter(sql, conn);
                }

                DataSet ds = new DataSet();
                ds.Reset();
                da.Fill(ds);
                conn.Close();

                for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
                {
                    String result = ds.Tables[0].Rows[i][0].ToString();
                    result = result.Replace("\"", "");
                    result = result.Replace("(", "");
                    result = result.Replace(")", "");
                    results = results + result + ",";
                }
                if (!results.Contains("Consumo desproporcionado de combustible"))
                {
                    results = results + "Consumo desproporcionado de combustible" + ",0,";
                }

                if (!results.Contains("Mezcla de Aire/Combustible muy pobre"))
                {
                    results = results + "Mezcla de Aire/Combustible muy pobre" + ",0,";
                }
                if (!results.Contains("Mezcla de Aire/Combustible muy rica"))
                {
                    results = results + "Mezcla de Aire/Combustible muy rica" + ",0,";
                }
                if (!results.Contains("Sensor MAF sucio o averiado"))
                {
                    results = results + "Sensor MAF sucio o averiado" + ",0,";
                }
                if (!results.Contains("Inyectores sucios o averiados"))
                {
                    results = results + "Inyectores sucios o averiados" + ",0,";
                }
                if (!results.Contains("Bobina Averiada"))
                {
                    results = results + "Bobina Averiada" + ",0,";
                }
                if (!results.Contains("Bujías propensas a daños"))
                {
                    results = results + "Bujías propensas a daños" + ",0,";
                }
                if (!results.Contains("Vehículo propenso a recalentamiento"))
                {
                    results = results + "Vehículo propenso a recalentamiento" + ",0,";
                }
                if (!results.Contains("Radiador Averiado"))
                {
                    results = results + "Radiador Averiado" + ",0,";
                }
                if (!results.Contains("Alternador defectuoso"))
                {
                    results = results + "Alternador defectuoso" + ",0,";
                }
                return results;
            }
            else
            {
                return "Por favor revise los datos ingresados";
            }
        }




        [System.Web.Services.WebMethod]
        public static String GetDatosFallas(String falla, String marca)
        {
            string connstring = String.Format("Server={0};Port={1};" +
                "User Id={2};Password={3};Database={4};",
                "localhost", "5432", "heydriver",
                "h3yDr1v3r", "heydriverdb");

            Boolean correcto = false;
            foreach (String brand in brands)
            {
                if (brand.Equals(marca))
                {
                    correcto = true;
                }
            }
            String results = "";
            if (correcto)
            {
                NpgsqlConnection conn = new NpgsqlConnection(connstring);
                conn.Open();
                NpgsqlDataAdapter da = new NpgsqlDataAdapter();
                if (falla.Equals("Sólo usar la marca"))
                {
                    string sql = "SELECT ALL_FAILURES_STATISTICS_by_brand('" + marca + "');";
                    da = new NpgsqlDataAdapter(sql, conn);
                }
                else
                {
                    string sql = "SELECT ALL_FAILURES_STATISTICS_by_model('" + marca + "', '" + falla + "');";
                    da = new NpgsqlDataAdapter(sql, conn);
                }

                DataSet ds = new DataSet();
                ds.Reset();
                da.Fill(ds);
                conn.Close();

                for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
                {
                    String result = ds.Tables[0].Rows[i][0].ToString();
                    result = result.Replace("\"", "");
                    result = result.Replace("(", "");
                    result = result.Replace(")", "");
                    results = results + result + ",";
                }
                if (!results.Contains("Consumo desproporcionado de combustible"))
                {
                    results = results + "Consumo desproporcionado de combustible" + ",0,";
                }

                if (!results.Contains("Mezcla de Aire/Combustible muy pobre"))
                {
                    results = results + "Mezcla de Aire/Combustible muy pobre" + ",0,";
                }
                if (!results.Contains("Mezcla de Aire/Combustible muy rica"))
                {
                    results = results + "Mezcla de Aire/Combustible muy rica" + ",0,";
                }
                if (!results.Contains("Sensor MAF sucio o averiado"))
                {
                    results = results + "Sensor MAF sucio o averiado" + ",0,";
                }
                if (!results.Contains("Inyectores sucios o averiados"))
                {
                    results = results + "Inyectores sucios o averiados" + ",0,";
                }
                if (!results.Contains("Bobina Averiada"))
                {
                    results = results + "Bobina Averiada" + ",0,";
                }
                if (!results.Contains("Bujías propensas a daños"))
                {
                    results = results + "Bujías propensas a daños" + ",0,";
                }
                if (!results.Contains("Vehículo propenso a recalentamiento"))
                {
                    results = results + "Vehículo propenso a recalentamiento" + ",0,";
                }
                if (!results.Contains("Radiador Averiado"))
                {
                    results = results + "Radiador Averiado" + ",0,";
                }
                if (!results.Contains("Alternador defectuoso"))
                {
                    results = results + "Alternador defectuoso" + ",0,";
                }
                return results;
            }
            else
            {
                return "Por favor revise los datos ingresados";
            }
        }
    }
}