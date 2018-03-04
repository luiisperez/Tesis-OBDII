using HeyDriverWebsite.Model;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HeyDriverWebsite.Controller
{
    public class controller
    {
        public String GetDatos(String marca, String modelo, List<String> brands, List<String> models)
        {
            model model = new model();
            return model.EstadisticasMarcasModelos(marca, modelo, brands, models);
        }


        public String GetDatosFallas(String falla, String marca, List<String> brands, List<String> models)
        {
            model model = new model();
            return model.EstadisticasFallasMarcas(falla, marca, brands, models);
        }


        public String GetFallasVIN(String vin)
        {
            model model = new model();
            return model.EstadisticasFallasVIN(vin);
        }
    }
}