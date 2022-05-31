<?php

namespace App\Models;

use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'name',
        'email',
        'sexe',
        'password',
    ];

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * The attributes that should be cast.
     *
     * @var array<string, string>
     */
    protected $casts = [
        'email_verified_at' => 'datetime',
    ];

    public function club(){
        return $this->HasOne('App\Models\club', 'responsableClub');
    }

    public function demandeCreationClubs(){
        return $this->HasMany('App\Models\DemandeCreationClub', 'responsableClubId');
    }

    public function roles(){
        return $this->belongsToMany('App\Models\Role')->withPivot(['status','department','classe']);}

    public function demandeAdhesionClub(){
        return $this->HasMany('App\Models\DemandeAdhesionClub', 'user_id');
    }

    public function demandeSalle(){
        return $this->HasMany('App\Models\DemandeSalle', 'idResponsable');
    }

    public function DemandeMateriel(){
        return $this->HasMany('App\Models\DemandeMateriel', 'idResponsableClub');
    }

    Public function materiel(){
        return $this->hasMany('App\Models\Materiel', 'idResponsable');
    }
    public function department(){
        return $this->hasOne('App\Models\Departement','chef_departement_id');
    }
  public function DemandeEventResponsable(){
        return $this->HasMany('App\Models\DemandeEvent', 'responsableClubId');
    }
    public function DemandeEventAdmin(){
        return $this->HasMany('App\Models\DemandeEvent', 'administrateurId');
    }

    public function DemandeAdhesionEventStudent(){
        return $this->HasMany('App\Models\DemandeAdhesionEvent', 'idStudent');
    }
    public function messages()
    {
        return $this->hasMany(Message::class);
    }

    public function matiere(){
        return $this->HasOne('App\Models\Matiere','idEnseignant');
    }

    public function classe(){
        return $this->belongsToMany(Role::class)->using(RoleUser::class)->with("RoleUser.class");
    }

    public function note(){
        return $this->HasMany('App\Models\Note', 'student_id');
    }
     public function Reclamation(){
        return $this->HasMany('App\Models\Reclamation', 'idResponsable');
    }
    public function emploieDeTemp(){
        return $this->HasOne('App\Models\EmploieDeTemp', 'idResponsable');
    }
}
