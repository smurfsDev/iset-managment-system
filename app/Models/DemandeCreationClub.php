<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class DemandeCreationClub extends Model
{
    protected $table='demandeCreationClub';
    protected $fillable = [
        "nomClub",
        "logo",
        "dateCreation",
        "activite",
        "president",
        "vicePresident",
        "responsableClubId"
    ];
    public function responsableClub(){
        return $this->belongsTo(User::class,'id');
    }


}
