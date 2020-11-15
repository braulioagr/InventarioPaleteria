using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.SqlClient;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PaleteriaInventario
{
    partial class Sucursal : Form
    {
        #region Variables de instancia
        private int id;
        private bool alta;
        private string[] valores;
        private SqlCommand comando;
        #endregion

        #region Constructores
        public Sucursal()
        {
            this.alta = true;
            valores = null;
            InitializeComponent();
        }
        public Sucursal(string[] valores)
        {
            this.alta = false;
            this.valores = valores;
            InitializeComponent();
        }

        private void Sucursal_Load(object sender, EventArgs e)
        {
            if (alta)
            {

                this.id = -1;
                this.comando = new SqlCommand("insert into empleado.Sucursal values(@direccion,@telefono,@horario);");
                comando.Parameters.Add(new SqlParameter("@direccion", SqlDbType.VarChar, 50));
                comando.Parameters.Add(new SqlParameter("@telefono", SqlDbType.VarChar, 13));
                comando.Parameters.Add(new SqlParameter("@horario", SqlDbType.VarChar, 11));
            }
            else
            {

                this.id = int.Parse(valores[0]);
                this.textBoxDireccion.Text = valores[1].Trim();
                this.textBoxTelefono.Text = valores[2].Trim();
                string[] horario = valores[3].Split('-');
                this.comboBoxApertura.Text = horario[0].Trim();
                this.comboBoxCierre.Text = horario[1].Trim();
                comando = new SqlCommand("update empleado.Sucursal "+
                                         "set direccion = @direccion, telefono = @telefono, horario = @horario "+
                                         "where idSucursal = @id;");
                comando.Parameters.Add(new SqlParameter("@direccion", SqlDbType.VarChar, 50));
                comando.Parameters.Add(new SqlParameter("@telefono", SqlDbType.VarChar, 13));
                comando.Parameters.Add(new SqlParameter("@horario", SqlDbType.VarChar, 11));
                comando.Parameters.Add(new SqlParameter("@id", SqlDbType.BigInt));
            }
        }
        #endregion

        private void buton1_Click(object sender, EventArgs e)
        {
            if (!string.IsNullOrEmpty(this.textBoxDireccion.Text) && !string.IsNullOrEmpty(this.textBoxTelefono.Text))
            {
                this.DialogResult = DialogResult.OK;
                if (alta)
                {
                    this.comando.Parameters[0].Value = this.textBoxDireccion.Text;
                    this.comando.Parameters[1].Value = this.textBoxTelefono.Text;
                    this.comando.Parameters[2].Value = this.comboBoxApertura.Text.Trim() + "-" + this.comboBoxCierre.Text.Trim();
                }
                else
                {
                    this.comando.Parameters[0].Value = this.textBoxDireccion.Text;
                    this.comando.Parameters[1].Value = this.textBoxTelefono.Text;
                    this.comando.Parameters[2].Value = this.comboBoxApertura.Text.Trim() + "-" + this.comboBoxCierre.Text.Trim();
                    this.comando.Parameters[3].Value = this.id;

                }
                this.Close();
            }
            else
            {
                MessageBox.Show("Uno de los campos esta vacio","Advertencia",MessageBoxButtons.OK, MessageBoxIcon.Warning);
            }
        }



        #region Gets & Sets

        public SqlCommand Comando
        {
            get
            {
                return this.comando;
            }
        }
        #endregion


    }
}
