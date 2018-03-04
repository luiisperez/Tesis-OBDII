using Npgsql;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace HeyDriverWebsite.Model
{
    public class model
    {
        public String EstadisticasMarcasModelos(String marca, String modelo, List<String> brands, List<String> models)
        {
            try
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
            catch (Exception ex)
            {
                return "Ha ocurrido un error, por favor vuelva a intentar";
            }
        }

        public String EstadisticasFallasMarcas(String falla, String marca, List<String> brands, List<String> models)
        {
            try
            {
                string connstring = String.Format("Server={0};Port={1};" +
                    "User Id={2};Password={3};Database={4};",
                    "localhost", "5432", "heydriver",
                    "h3yDr1v3r", "heydriverdb");

                Boolean correcto = false;
                if (marca.Equals("Todas las marcas"))
                {
                    correcto = true;
                }
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
                    if (marca.Equals("Todas las marcas"))
                    {
                        string sql = "SELECT ALL_BRANDS_STATISTICS_BY_FAILURE('" + falla + "');";
                        da = new NpgsqlDataAdapter(sql, conn);
                    }
                    else
                    {
                        string sql = "SELECT ALL_MODELS_STATISTICS_BY_FAILURE('" + falla + "', '" + marca + "');";
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
                    try
                    {
                        if (results.Split(',').Length < 10)
                        {
                            results = results + ", , , , , , , , , , ";
                        }
                    }
                    catch (Exception ex) { }
                    return results;
                }
                else
                {
                    return "Por favor revise los datos ingresados";
                }
            }
            catch (Exception ex)
            {
                return "Ha ocurrido un error, por favor vuelva a intentar";
            }
        }

        public String EstadisticasFallasVIN(String vin)
        {
            try
            {
                string connstring = String.Format("Server={0};Port={1};" +
                    "User Id={2};Password={3};Database={4};",
                    "localhost", "5432", "heydriver",
                    "h3yDr1v3r", "heydriverdb");
                String results = "";
                if (!vin.Equals(""))
                {
                    NpgsqlConnection conn = new NpgsqlConnection(connstring);
                    conn.Open();
                    NpgsqlDataAdapter da = new NpgsqlDataAdapter();
                    string sql = "SELECT failurebrandname, failurebrandmodel, failuredescription FROM FAILURE WHERE FAILURECARSERIAL = '" + vin + "';";
                    da = new NpgsqlDataAdapter(sql, conn);

                    DataSet ds = new DataSet();
                    ds.Reset();
                    da.Fill(ds);
                    conn.Close();

                    for (int i = 0; i < ds.Tables[0].Rows.Count; i++)
                    {
                        String brand = ds.Tables[0].Rows[i][0].ToString();
                        String model = ds.Tables[0].Rows[i][1].ToString();
                        String failure = ds.Tables[0].Rows[i][2].ToString();
                        results = results + brand + "," + model + "," + failure + "/";
                    }
                    return results;
                }
                else
                {
                    return "Por favor revise los datos ingresados";
                }
            }
            catch (Exception ex)
            {
                return "Ha ocurrido un error, por favor vuelva a intentar";
            }
        }
    }
}