using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PaleteriaInventario
{
    public partial class AltaRestock : Form
    {
        public delegate void ActualizaDataGrid(DataGridView dataGrid);
        public event ActualizaDataGrid actualizaDatagrid;
        int idProducto;
        public AltaRestock()
        {
            InitializeComponent();
        }

        private void AltaRestock_Load(object sender, EventArgs e)
        {
            this.idProducto = -1;
            this.actualizaDatagrid(this.dataGridViewProducto);
        }

        private void dataGridViewProducto_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            this.idProducto = int.Parse(this.dataGridViewProducto.CurrentRow.Cells[0].Value.ToString());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (this.idProducto != -1)
            {
                this.DialogResult = DialogResult.OK;
                this.Close();
            }
            else
            {
                MessageBox.Show("Por favor primero seleccione un producto","Error",MessageBoxButtons.OK,MessageBoxIcon.Warning);
            }
        }
        public int IdProducto
        {
            get { return this.idProducto; }
        }

        public int cantidad
        {
            get { return (int)this.numericUpDown1.Value; }
        }
    }
}
