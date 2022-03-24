<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeMateriel_Materiel extends Model
{
    use HasFactory;

    protected $table='demande_materiel__materiels';
    protected $fillable = [
        "idDemande",
        "idMateriel",
        "quantité",
    ];
    public function materiel(){
        return $this->hasOne(Materiel::class);
    }

}
